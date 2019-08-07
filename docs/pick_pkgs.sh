#!/bin/bash

usage () {
cat << EOF

# This script is used to update both of common_pkgs_pool_akraino_stx_7-8-19.lst andPackages_pool_7-26-2-19.csv
# since we need to prioritize the porting work of common packages between both projects(Akraino and Stx)
# so, it is the time to cook a helper script for the package pool maintanence.

pick_pkgs.sh -h
pick_pkgs.sh -n first_number_of_pkg 
pick_pkgs.sh -p pkg_list_whitespace_seprated_quoted

# if no parameter given, the default first 2 packages will be picked for your confirmation.
# -n option will be used if both of -n and -p are specified.

eg.

pick_pkgs.sh -n 5
pick_pkgs.sh -p "abc xyz 123"


EOF
exit 0
}

NOW_LIST_FILE="common_pkgs_pool_akraino_stx_7-8-19.lst"
STX_LIST_FILE="Packages_pool_7-26-2-19.csv"

while getopts "hn:p:" arg; do
  case $arg in
    h)
      usage
      ;;
    n)
      if [[ $OPTARG =~ ^[0-9]+$ ]];then
        number=$OPTARG
      else
        number=2
      fi
      ;;
    p)
      pkgs=$OPTARG
      ;;
    \?)
      echo "WRONG" >&2
      ;;
  esac
done

if [ $OPTIND -eq 1 ]; then number=2; fi

echo $number
echo $pkgs

push_reminder() {

cat << EOF

Following packages have been picked and related files have been updated
Please push the package pool files to upstream ASAP, and create issues 
accordingly in following link:
https://github.com/zbsarashki/meta-starlingX/issues

$1

EOF

}

do_update() {
  for pkg in $1
  do
    sed -i "/^$pkg$/d" $NOW_LIST_FILE
    sed -i "/$pkg:/d" $STX_LIST_FILE
  done 
}


if [ ! -z "$number" ]
then
  pkgs_picked=`head -n $number $NOW_LIST_FILE` 
else
  if [ ! -z "$pkgs" ]
  then
    for pkg in $pkgs
    do
      grep $pkg $NOW_LIST_FILE
      if [ "$?" -eq "0" ]; then pkgs_picked="$pkgs_picked $pkg"; fi 
    done
  fi
fi

if [ ! -z "$pkgs_picked" ]
then
  while true; do
  echo -e "Following packages are picked and will update $NOW_LIST_FILE and $STX_LIST_FILE:\n"
  echo -e "$pkgs_picked\n"

  read -p "Do you wish to pick these packages?[y or n]" yn
  case $yn in
    [Yy]* ) do_update "$pkgs_picked" && push_reminder "$pkgs_picked"; break;;
    [Nn]* ) exit;;
    * ) echo "Please answer yes or no.";;
  esac
  done
fi
