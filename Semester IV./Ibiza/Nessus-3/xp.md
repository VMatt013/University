

Name | Solution |
-----| -----|
MS08-067: Microsoft Windows Server Service Crafted RPC Request Handling Remote Code Execution (958644) (ECLIPSEDWING) (uncredentialed check) | Windows update |
Microsoft Windows XP Unsupported Installation Detection | Windows update
Unsupported Windows OS (remote) | Windows update
MS05-027: Vulnerability in SMB Could Allow Remote Code Execution (896422) (uncredentialed check) | Windows update
MS06-040: Vulnerability in Server Service Could Allow Remote Code Execution (921883) (uncredentialed check) | Windows update
MS09-001: Microsoft Windows SMB Vulnerabilities Remote Code Execution (958687) (uncredentialed check) | Windows update
MS17-010: Security Update for Microsoft Windows SMB Server (4013389) (ETERNALBLUE) (ETERNALCHAMPION) (ETERNALROMANCE) (ETERNALSYNERGY) (WannaCry) (EternalRocks) (Petya) (uncredentialed check) | Windows update / discontinue the use of SMBv1
MS06-035: Vulnerability in Server Service Could Allow Remote Code Execution (917159) (uncredentialed check) | Windows update
SMB Signing not required | Enforce message signing in the host's configuration. 

## SMB NULL Session Authentication
1. Network access: Allow anonymous SID/Name translation
2. Network access: Do not allow anonymous enumeration of SAM accounts
3. Network access: Do not allow anonymous enumeration of SAM accounts and shares
4. Network access: Let Everyone permissions apply to anonymous users
5. Network access: Named Pipes that can be accessed anonymously
6. Network access: Shares that can be accessed anonymously