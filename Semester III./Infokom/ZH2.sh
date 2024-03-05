# Feladat 1.
(SW & R)
banner motd #Authorized Access Only!#
enable secret cisco
line console 0
password cisco
login
line vty 0 15
password cisco
login
service password-encryption
ip domain-name cisco.com
crypto key generate rsa
username admin secret cisco
line vty 0 15
transport input ssh
login local
ip ssh version 2
copy running-config startup-config

# Feladat 2.
(SW)
vlan 50
name EDUROAM

vlan 60
name TO

vlan 70
name OKTATOK

(SW2 & SW3)
int f0/24
sw m access
sw access vlan 70

int f0/23
sw m access
sw access vlan 60

int f0/22
sw m access
sw access vlan 50

# 3. Feladat
(SW1-SW3)
int range f0/1-2
channel-group 1 mode active
interface port-channel 1

int range f0/3-4
channel-group 2 mode active
interface port-channel 2

int range f0/5-6
channel-group 3 mode desirable
interface port-channel 3


switchport mode trunk
switchport trunk allowed vlan 50,60,70

# 4. Feladat
(SW1-3)
spanning-tree mode rapid-pvst
(SW3)
spanning-tree vlan 50,60,70 root primary

copy running-config startup-config

# 5. Feladat
(R1)
int g0/2.50
encapsulation dot1Q 50
ip add 10.10.50.1 255.255.255.0
exit

int g0/2.60
encapsulation dot1Q 60
ip add 10.10.60.1 255.255.255.0
exit

int g0/2.70
encapsulation dot1Q 70
ip add 10.10.70.1 255.255.255.0
exit

int g0/2
no shut
end

# 8. Feladat
(R1)
ip dhcp pool P50
network 10.10.50.0 255.255.255.0
default-router 10.10.50.1
dns-server 8.8.8.8
domain-name example.com

ip dhcp pool P60
network 10.10.60.0 255.255.255.0
default-router 10.10.60.1
dns-server 8.8.8.8
domain-name example.com

ip dhcp pool P70
network 10.10.70.0 255.255.255.0
default-router 10.10.70.1
dns-server 8.8.8.8
domain-name example.com

# 9. Feladat