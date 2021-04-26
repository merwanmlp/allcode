#Merwan Malakapalli - Honors Precalculus - 10/22/19
#Python 1a- PolySeq.py
#Given the coefficients of numbers in a sequence to degree 3, this program prints the equation, a number of terms that the user asks, and the partial sum of a number of terms that the user asks


#importing modules
import numpy as np;

#Main Program starts here
print("Given the coefficients for a polynomial sequence, this program generates the ");
print("equation for the terms, a number of terms that you want, and the sum of a number of terms that you want");

##INPUT##
termList = np.array([]);
for i in range(4):
    temp = float(input("Enter term a" + str(i) + ": "));
    termList = np.append(termList, temp);
numTerms = int(input("How many terms? "));
numSum = int(input("How many terms do you want in your sum? "));

##CALCULATIONS##
#Printing the equation using inputs. Adds a0 to the preexisting equation to get rid of the k^0 in the final output.
equation = "Pk = " + str(termList[0]) + " + ";
#Since we got rid of k^0 and a0, i needs to go up 1 when printing the equation because it starts at zero and printing a0*k^0 twice would be repetitive.
for i in range(4):
    #This if statement only exists to get rid of the final plus sign
    if(i == 2):
        equation += "(" + str(termList[i+1]) + ")*k^" + str(i+1);
        break;
    equation += "(" + str(termList[i+1]) + ")*k^" + str(i+1) + " + ";


#Creating a string that will hold the terms. 
finalStr = "{ " 
term = 0;
for k in range(numTerms):
    #Calculates term
    term = termList[0] + (termList[1]*(k+1)**1) + termList[2]*(k+1)**2 + termList[3]*(k+1)**3;
    #Exists to get rid of the final comma
    if(k == numTerms-1):
        finalStr += str(term);
        break;
    #Adds term to the string that holds terms.
    finalStr += str(round(term, 2)) + ", ";
 
#finishes the string
finalStr += " }";

#Calculates the sum
#sum for cubes
cubesSum = termList[3] * ( (numSum**2 * (numSum+1)**2)/4 );
#sum for squares
squaredSum = termList[2] * ( numSum *(numSum+1) * (2*numSum+1))/6;
#sum for degree 1
firstSum = termList[1] * (numSum*(numSum+1)/2);
#sum for degree 0
termZeroSum = termList[0]*numSum;
#final sum adds up all the previous sums.
finalSum = cubesSum + squaredSum + firstSum + termZeroSum;
    
##OUTPUT##
print("The equation for your terms is:");
print(equation);
print();
print("The " + str(numTerms) + " terms that you requested to be shown are:")
print(finalStr);
print();
print("The sum of the first " + str(numSum) + " terms are:")
print(round(finalSum, 2));


    
    
    