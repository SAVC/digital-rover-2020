#!/usr/bin/env python
# -*- coding: utf-8 -*-

import socket
import sys
from datetime import datetime, date, time

#Данный функционал еще не оттестирован!!!!!

s = socket.socket()
s.bind(("localhost",5000))
s.listen(10) 
sc, address = s.accept()

print address
i=1
f = open('./yolov5/inference/images'+ str(datetime.now())+".jpg",'wb') # Open in binary
i=i+1
while (True):

    l = sc.recv(1024)
    while (l):
        f.write(l)
        l = sc.recv(1024)
f.close()

sc.close()
s.close()

