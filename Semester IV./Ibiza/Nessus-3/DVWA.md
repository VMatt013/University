## Apache 
Update Apache

(CVE-2010-0434) :A flaw in the core sub-request process code can lead to sensitive information from a request being handled by the wrong thread if a multi-threaded environment is used. 

## phpMyAdmin prior to 4.8.6 SQLi vulnerablity (PMASA-2019-3)
Upgrade to phpMyAdmin version 4.8.6 or later
or use patches
a
## Apache httpd SEoL (2.1.x <= x <= 2.2.x)
Upgrade to a version of Apache HTTP Server that is currently supported.

## PHP Unsupported Version Detection
upgraded php

## PHP 5.3.x < 5.3.15 Multiple Vulnerabilities
(CVE-2011-1148)
Use-after-free vulnerability in the substr_replace function in PHP 5.3.6 and earlier allows context-dependent attackers to cause a denial of service (memory corruption) or possibly have unspecified other impact by using the same variable for multiple arguments.
upgraded php


## OpenSSL < 0.9.8p / 1.0.0b Buffer Overflow
Upgrade to OpenSSL 0.9.8p / 1.0.0b or later.

## phpMyAdmin Installation Not Password Protected
Restrict access to phpMyAdmin using one of the methods referred to in the vendor's documentation.

## Apache 2.2.x < 2.2.24 Multiple XSS Vulnerabilities
- Errors exist related to the modules mod_info, mod_status, mod_imagemap, mod_ldap, and mod_proxy_ftp and unescaped hostnames and URIs that could allow cross- site scripting attacks. (CVE-2012-3499)
Upgrade Apache or don't use the modules

## PHP PHP_RSHUTDOWN_FUNCTION Security Bypass
An error exists related to the function 'PHP_RSHUTDOWN_FUNCTION' in the libxml extension and the 'stream_close' method that could allow a remote attacker to bypass 'open_basedir' protections and obtain sensitive information
Upgrade php

## PHP expose_php Information Disclosure
In the PHP configuration file, php.ini, set the value for 'expose_php' to 'Off' to disable this behavior. Restart the web server daemon to put this change into effect.

## phpMyAdmin setup.php Verbose Server Name XSS (PMASA-2010-7)
A remote attacker could exploit this by tricking a user into executing arbitrary script code.
Upgrade to phpMyAdmin 3.3.7 or later.