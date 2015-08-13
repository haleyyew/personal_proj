#HW3:Q1.7.6
def calc (df, init, h, end):

    steps = int(end/h)
    init = init
    h_accum = 0
    
    for i in range(0,steps):
        y = init + df*init*h
        h_accum = h_accum + h
        print ('f('+str(h_accum)+') =' +str(y))
        init = y
        
        
calc(-5, 1, 0.25, 4)