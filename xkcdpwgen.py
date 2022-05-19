#!/usr/bin/env python3
#encoding utf-8
'''
Created on Feb 1, 2019

@author: mdsco
xkcdpwgen --     

xkcdpwgen is a program that generates secure, memorable passwords using the XKCD method.
See https://www.xkcd.com/936/
'''
import sys
import os 
import random

from argparse import ArgumentParser

def main(argv=None):
    'command line options'
    
    if argv is None:
        argv = sys.argv
    else:
        sys.argv.extend(argv)
    
    program_name = os.path.basename(sys.argv[0])
    
    try:
        # Setup argument parser
        parser = ArgumentParser(prog="xkcdpwgen", description="Generate a secure, memorable password for the user")
        parser.add_argument("-w", "--words", dest="WORDS", default=4, type=int, help = "include WORDS words in the password (default=4")
        parser.add_argument("-c", "--caps", dest="CAPS", default=0, type=int, help = "capitalize the first letter of CAPS random words (default=0")
        parser.add_argument("-n", "--numbers", dest="NUMBERS", default=0, type=int, help = "insert NUMBERS numbers in the password (default=0")
        parser.add_argument("-s", "--symbols", dest="SYMBOLS", default=0, type=int, help = "insert SYMBOLS symbols in the password (default=0")
        
        #processes arguments
        args = parser.parse_args()
        
        #define variables from command line
        nbrWords =args.WORDS
        nbrCaps = args.CAPS 
        nbrNumbers = args.NUMBERS
        nbrSymbols = args.SYMBOLS
        
        symbols = ['~','!', '@', '#', '$', '%', '^', '&', '*', '.', ':', ';']
        
        #validate command line arguments
        
        if nbrWords < 0:
            print ("Error: WORDS cannot be less than 0")
            return 1
        if nbrCaps < 0:
            print ("Error: CAPS cannot be less than 0")
            return 1
        if nbrCaps > nbrWords:
            print ("Error: CAPS cannot exceed WORDS")
            return 1
        
        if nbrNumbers < 0:
            print ("Error: NUMBERS cannot be less than 0")
            return 1
        if nbrSymbols < 0:
            print ("Error: SYMBOLS cannot be less than 0")
        
        words = [line.rstrip('\n') for line in open ("wordlist.txt")]
        
        password = ""
        passwordlst = []
        capsIndex = []
        numIndex = []
        symIndex = []         
        if nbrCaps > 0:
            for i in range(nbrCaps):
                index = random.randint(0, nbrWords-1)
                while index in capsIndex:
                    index = random.randint(0, nbrWords-1)
                capsIndex.append(index)
        
        if nbrNumbers > 0:
            for i in range (nbrNumbers):
                index = random.randint(0, nbrWords-1)
                numIndex.append(index)
                
        if nbrSymbols > 0:
            for i in range (nbrSymbols):
                index = random.randint(0, nbrWords-1)
                symIndex.append(index)
                       
        for i in range(nbrWords):
            randIndex = random.randint(0, len(words) -1)
            if nbrCaps > 0 or nbrNumbers > 0 or nbrSymbols > 0:
                current = words[randIndex]
                if i in capsIndex:
                    current = current.capitalize()
                
                for j in numIndex:
                    if i == j:
                        pos = random.randint(0,1)
                        num = random.randint(0,9)
                        if pos == 0:
                            current = str(num) + current
                        if pos == 1:
                            current = current + str(num)
                
                for k in symIndex:
                    if i == k:
                        pos = random.randint(0,1)
                        sym = random.randint(0, (len(symbols) - 1))
                        if pos == 0:
                            current = symbols[sym] + current
                        if pos == 1:
                            current = current + symbols[sym]
                passwordlst.append(current)
                
            else:
                passwordlst.append(words[randIndex])
        
        for i in passwordlst:
            password += i
  
        print (password)
            
            
            
        
        return 0
    except KeyboardInterrupt:
        ### handle keyboard interrupt ###
        return 0
    except Exception as e:
        indent = len(program_name) * " "
        sys.stderr.write(program_name + ": " + repr(e) + "\n")
        sys.stderr.write(indent + "  for help use --help")
        return 2

if __name__ == "__main__":
    sys.exit(main())
        
