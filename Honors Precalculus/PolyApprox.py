#Merwan M, David C, Nate C - Honors Precalculus Polynomial Approximations
#This program can find the value of any sin, cos, or tan function, but the value for the sin function must be between 0 and pi/4 radians.
import numpy as np;
import math;

 

PI = np.pi; # class constants
TRIG_CYCLE = np.array([0, 1, 0, -1]) # the cycle of values at x = 0 for the sin(x) and its derivatives
ACCURACY = 15; # how many derivatives calculated down to

 


### INPUTS ### 
def functionChoice():
    # prints introduction
    print("This program finds the values of trigonometric functions");
    print("1. sin(x)");
    print("2. cos(x)");
    print("3. tan(x)");
    print();
  
    # gathers input
    num = float(input("Select the function that you want to evaluate (1, 2, 3): "));
    return num;

 

    
def angle():
    # gathers angle
    inputString = str(input("Enter the angle x in radians: "));
    
    # tests to see if user inputs in terms of PI, parses for the fraction, 
    # and multiples fraction by pi to convert to float
    if (inputString[len(inputString) - 1] == 'I' or inputString[len(inputString) - 1] == 'i'):
        index = 0;
        character = '';
        fraction = '';
        while (character != '*'):
            character = inputString[index];
            if (character != '*'):
                fraction += character;
            index += 1;
        angle = eval(fraction) * PI;
    else:
        angle = float(inputString);
        
    return angle;
    

 

### CALCULATIONS ###
def refAngle(angle):
    # stores original angle and sets default  signs
    original = angle;
    sign = 1;
    cosSign = 1;
    
    # if the angle is negatives, adds 2*PI until it is positives
    # so that it works for upcoming if statements
    if (angle < 0):
        while (angle < 0):
            angle += (2 * PI);
        
    # if angle is too large, mods until it is within first rotation
    if(angle > 0):
        angle = angle % (2*PI);
        
    # finds reference angle and signs for second quadrant
    if(angle >= PI / 2 and angle <= PI):
        angle = round(PI - angle, 4);
        cosSign = -1;
    # finds reference angle and signs for third quadrant    
    elif(angle > PI and angle <= 3*PI/2):
        angle = round(angle - PI, 4);
        sign = -1;
        cosSign = -1;
    # finds reference angle and signs for fourth quadrant
    elif(angle > 3*PI/2 and angle <= 2*PI):
        angle = round(2*PI - angle, 4);
        sign = -1;
        
    return np.array([sign, cosSign, angle, original]);
        
      
def coreFunction(angle):
    # necessary variables to be used later
    newAngle = refAngle(angle)[2];
    polynomial = np.zeros(ACCURACY+1);
  
    # using linear approximations methods, identified pattern
    # where the value of the derivative @ x = 0 is always divided
    # by the factorial (because of the power rule), which is the coefficient
    # of the variable
    for i in range(ACCURACY): 
        polynomial[i] = TRIG_CYCLE[i % 4]/math.factorial(i);
        
    # uses the polynomial to calculate y value @ x = new angle
    
    output = polyEval(polynomial, newAngle);
    return output;

 

def cosine (angle):
    # uses the relationship between sine and cosine to find sine
    # using pythag theorem was considered, but had limitations
    return coreFunction(PI / 2 - angle);

 

def tangent(angle):
    # to prevent division by 0 error, returns undefined
    # if cosine is 0
    if(cosine(angle) == 0.0):
        return "undefined";
    
    return coreFunction(angle)/cosine(angle);

 

def polyEval(coeff, inputs):
    output = 0;
    numDegree = len(coeff);
    term= 0;
    
    # evalutes output by looping through terms of polynomial 
    # one at a time
    for k in range(numDegree):
        term += coeff[k] * (inputs**k);
    output = term;
    return output;

 


### OUTPUTS ###
def output(functionNum, angle):
    # calculates, formats, and outputs sine
    if(functionNum == 1.0):
        print("sin(" + str(round(angle[3], 4)) + ") = " + str(angle[0]*coreFunction(angle[2])));
        
    # calculates, formats, and outputs cosine
    elif(functionNum == 2.0):
        print("cos(" + str(round(angle[3], 4)) + ") = " + str(angle[1]*cosine(angle[2])));
        
    # calculates, formats, and outputs tangent
    else:
        if(tangent(angle[2]) == "undefined"):
            print("tan(" + str(round(angle[3], 4)) + ") = undefined");
        else:
            print("tan(" + str(round(angle[3], 4)) + ") = " + str((angle[0]/angle[1])*tangent(angle[2])));
  
  
  
#####MAIN#####
##INPUTS##   
functionNum = functionChoice(); 
angle = refAngle(angle());

 

##OUPUTS##
output(functionNum, angle);