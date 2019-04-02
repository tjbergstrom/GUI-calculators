# Ty Bergstrom
# February 3, 2019
# Assignment 1, part 1
# Solution for RPN calculator
# main RPN module
# use input expression as argument for calculate method
# enter on command line RPN.py "2 2 * " to run

import myCalculate
import argparse


# get input and do stuff
def main():
    running = True
    while running:
        # get input
        parser = argparse.ArgumentParser()
        parser.add_argument('exp', help="Enter expression")
        args = parser.parse_args()
        # input error handling (doesn't work yet)
        try:
            # split input and send to calculation method
            print("Answer = ", myCalculate.calculate(args.exp.split()))
        except ValueError:
            e = sys.exc_info()[0]
            print(e)
        running = False


main()
