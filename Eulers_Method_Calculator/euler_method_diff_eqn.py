#Euler's method to calculate first order linear equations:
#x_i+1 = x_i + h; y_i+1 = y_i + h*f(x_i, y_i)
#where h is the step size
#This method is a SOMETIMES a good approximation of what the solution will be

#First order linear equations example: y' - y = 5
#Linear means the coefficients of the equation is a constant
#First order means there's only y' but no y'' and so on
#normally (not Euler's method) we just integrate both side to solve the equation

#HW3:Q1.7.6 from Lebl - Differential Equations for Engineers (2013)
#Take y' = -5y, y(0) = 1. 
#start with h = 0.25
#try to approximate y(4).

#Iterative approach:
def calc (df, init, h, end, results):
    """
    df is the differential, in this case it's the coefficient of -5y = -5
    init is the initial value, y(0)=1
    h is the step size, in this case it's 0.25
    end is when to stop, in this case it's 4
    results is used to store what the solution is at each step until end
    """
    
    steps = int(end/h) #number of steps to interate
    init = init
    h_accum = 0        #accumulated value for the indepependent variable x
    
    
    for i in range(0,steps):
        y = init + df*init*h  #df*init*h means h*f(x_i, y_i) = h*(-5)*(y_i)
        h_accum = h_accum + h #update x
        #print ('f('+str(h_accum)+') =' +str(y))
        results.append('y('+str(h_accum)+') = ' +str(y))
        init = y              #update solution to y' at this step = y_i
        
    return results

#Recursive approach:
def calc_recur (df, init, h, end, h_accum, results):
    """
    df is the differential, in this case it's the coefficient of -5y = -5
    init is the initial value, y(0)=1
    h is the step size, in this case it's 0.25
    end is when to stop, in this case it's 4
    h_accum is the accumulated value for the indepependent variable x, it is 
        initially equal to the initial value, in this case, 0
    """    
    if h_accum >= end:
        return
    
    y = init + df*init*h
    h_accum = h_accum + h
    #print ('f('+str(h_accum)+') =' +str(y))
    results.append('y('+str(h_accum)+') = ' +str(y))

    calc_recur (df, y, h, end, h_accum, results)
        
    #Note a non-tail recursion solution could be more elegant
   

def get_result (df, init, h, end, h_accum):
    """
    Turn results of calculation to string.
    
    Sample calculation using this function:  
    results = []
    calc(-5, 1, 0.25, 4, results)
    calc_recur(-5, 1, 0.25, 4, 0, results)
    print(results)
    """
    results = []
    calc_recur(df, init, h, end, h_accum, results)
    string = ''
    for item in results:
        string = string + item + "\n"
    return string

#Sample calculation:
#print (get_result (-5, 1, 0.25, 4, 0))

#NOTE: this function calculates of the form : y' - y = C and C needs to be 0
#But what if y' = -5x, or y' = y^2 ?
#The function doesn't calculate any other types of first order linear diff eqns.