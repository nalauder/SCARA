#!/usr/bin/python

from scapy.all import *
for i in range(3,15):
	packet_to_send= ARP(op=ARP.who_has, psrc='192.168.56.2', pdst='192.168.56.'+str(i))

	results = sr1(packet_to_send, iface='eth1')

	result = results[0]

	print result[ARP].psrc + "=" + result[ARP].hwsrc

