## phpMyAdmin prior to 4.8.6 SQLi vulnerablity (PMASA-2019-3)
Upgrade to phpMyAdmin version 4.8.6 or later.

## Drupal Database Abstraction API SQLi
The remote web server is running a PHP application that is affected by a SQL injection vulnerability.
Upgrade to version 7.32 or later.

## Apache Server ETag Header Information Disclosure
The remote web server is affected by an information disclosure vulnerability.
Modify the HTTP ETag header of the web server to not include file inodes in the ETag header calculation. Refer to the linked Apache documentation for more information.

## Browsable Web Directories
Some directories on the remote web server are browsable.
Make sure that browsable directories do not leak confidential information or give access to sensitive resources. Additionally, use access restrictions or disable directory indexing for any that do.

## HTTP TRACE / TRACK Methods Allowed
Debugging functions are enabled on the remote web server.
Disable these HTTP methods.

## nginx < 1.17.7 Information Disclosure
The remote web server is affected by an information disclosure vulnerability.
Upgrade to nginx version 1.17.7 or later.

## CGI Generic Cookie Injection Scripting
Restrict access to the vulnerable application.

## CGI Generic HTML Injections
The remote web server may be vulnerable to IFRAME injections or cross-site scripting attacks :
- IFRAME injections allow 'virtual defacement' that might scare or anger gullible users. Such injections are sometimes implemented for 'phishing' attacks. 

## Web Server Allows Password Auto-Completion

Add the attribute 'autocomplete=off' to these fields to prevent browsers from caching credentials.