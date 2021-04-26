import numpy as np;
#Asking for input from user
numDegree = int(input("To what degree do you want your sequence? "));
termList = np.array([]);
for i in range(numDegree + 1):
    temp = float(input("Enter term a" + str(i) + ": "));
    termList = np.append(termList, temp);
numTerms = int(input("How many terms? "));
numSum = int(input("How many terms do you want in your sum? "));


#Printing the equation using inputs
equation = "Pk = ";
for i in range(numDegree+1):
    #This if statement only exists to get rid of the final plus sign
    if(i == 0):
        equation += str(termList[i]);
        continue;
        
    if(termList[i] < 0):
        equation += " - " + str(abs(termList[i])) + "*k^" + str(i); 
        continue;
        
    if(termList[i] == 0):
        continue;
    
    if(i == 0):
        equation += " + " + str(termList[i]);
        continue;
    
    equation += " + " + str(abs(termList[i])) + "*k^" + str(i);


#Creating a string that will hold the terms. 
finalStr = "{ " 
term = 0;
#Nested for loops for the degree. 
for k in range(numTerms):
    for j in range(numDegree+1):
        #Calculates the term
        term += (termList[j] * ((k+1)**j));
        
    if(k == numTerms-1):
        finalStr += str(term);
        break;
    finalStr += str(term) + ', ';
    term = 0;

#finishes the string
finalStr += " }";
    
#Sum
finalSum = 0;
termSum = 0;
for i in range(numSum):
    for j in range(numDegree+1):
        termSum += (termList[j] * ((i+1)**j));

    finalSum += termSum;
    termSum = 0;
##OUTPUT##
print(equation);
print(finalStr);
print(round(finalSum));


    
    
    