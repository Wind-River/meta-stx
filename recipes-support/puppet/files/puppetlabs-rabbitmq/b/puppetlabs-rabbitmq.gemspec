Gem::Specification.new do |s|
  s.name        = 'puppetlabs-rabbitmq'
  s.version     = '5.6.0'
  s.date        = '2017-01-09'
  s.summary     = "Installs, configures, and manages RabbitMQ."
  s.description = s.summary
  s.authors     = ["Puppet Labs"]
  s.email       = ''
  s.files       = %w(LICENSE README.md Rakefile) + Dir.glob('{lib,spec}/**/*')
  s.homepage    = 'https://github.com/puppetlabs/puppetlabs-rabbitmq'
  s.license     = 'Apache 2.0'
 end
