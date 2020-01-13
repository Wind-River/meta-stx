PACKAGES += "stx-platform-helm"

DEPENDS += " helm-native openstack-helm-infra"

APP_STAGING = "${WORKDIR}/build_home/app_staging"
APP_NAME = "platform-integ-apps"
APP_TARBALL = "${APP_NAME}-${PV}-${TIS_PATCH_VER}.tgz"
APP_FOLDER = "/usr/local/share/applications/helm"

do_configure_prepend() {
	echo "Configure stx-platform-helm ..."
} 

do_compile_prepend() {

	export HELM_HOME="${WORKDIR}/build_home/.helm"
	export HELM_FOLDER="${STAGING_LIBDIR}/helm"
	export toolkit_version="${HELM_TOOL_KIT_VERSION}"
	STX_PKG_SRC="${S}/kubernetes/platform/stx-platform/stx-platform-helm/stx-platform-helm"

	cd ${STX_PKG_SRC}

        mkdir -p ${HELM_HOME}/repository/{{cache,local},plugins,starters,cache,cache/archive}

        # Stage a repository file that only has a local repo
        cp ${STX_PKG_SRC}/files/repositories.yaml ${HELM_HOME}/repository/repositories.yaml

        # Stage a local repo index that can be updated by the build
        cp ${STX_PKG_SRC}/files/index.yaml ${HELM_HOME}/repository/local/index.yaml


        ## Stage helm-toolkit in the local repo
        cp  ${HELM_FOLDER}/helm-toolkit-${toolkit_version}.tgz ${STX_PKG_SRC}

	cp -r ../../../../helm-charts/{ceph-pools-audit,node-feature-discovery,rbd-provisioner} .

        # Host a server for the charts
        helm serve --repo-path . &
        helm repo rm local
        helm repo add local http://localhost:8879/charts

        # Make the charts. These produce a tgz file
        make rbd-provisioner
        make ceph-pools-audit
        make node-feature-discovery

        # Terminate helm server (the last backgrounded task)
        kill %1

        # Create a chart tarball compliant with sysinv kube-app.py

        # Setup staging
        mkdir -p ${APP_STAGING}
        cp files/metadata.yaml ${APP_STAGING}
        cp manifests/manifest.yaml ${APP_STAGING}
	cp manifests/manifest.yaml ${APP_STAGING} 
	
	mkdir -p ${APP_STAGING}/charts
	cp ceph-pools-audit-0.1.0.tgz helm-toolkit-0.1.0.tgz \
		node-feature-discovery-0.3.0.tgz \
		rbd-provisioner-0.1.0.tgz ${APP_STAGING}/charts 

	
	# Populate metadata
	sed -i "s/@APP_NAME@/${APP_NAME}/g" ${APP_STAGING}/metadata.yaml
        sed -i "s/@APP_VERSION@/${PV}-${TIS_PATCH_VER}/g" ${APP_STAGING}/metadata.yaml
        sed -i "s/@HELM_REPO@/${HELM_REPO}/g" ${APP_STAGING}/metadata.yaml

        ## package it up
        cd ${APP_STAGING}
        find ./ -type f ! -name '*.md5' -exec md5sum {} + > checksum.md5
        tar -czf ${STX_PKG_SRC}/${APP_TARBALL} -C ${APP_STAGING} ./
	cd ${S}
}

do_install_prepend() {
	echo "Installing stx-platform-helm ..."
	STX_PKG_SRC="${S}/kubernetes/platform/stx-platform/stx-platform-helm/stx-platform-helm"
	install -d -m 0755 ${D}/${APP_FOLDER}
	install -d -m 0755 ${D}/opt/extracharts
	install -p -D -m 755 ${STX_PKG_SRC}/${APP_TARBALL} ${D}/${APP_FOLDER}
	install -p -D -m 755 ${STX_PKG_SRC}/node-feature-discovery-*.tgz ${S}/opt/extracharts
}

FILES_stx-platform-helm = " \
	${APP_FOLDER} \
	/opt/extracharts \
	"
 
