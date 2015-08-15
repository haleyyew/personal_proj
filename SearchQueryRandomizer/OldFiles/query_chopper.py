from random import shuffle

file = open('titles2.txt', 'r')

large_list = []
for line in file:
    line_split = line.split()
    large_list.append(line_split)
    
#print (large_list)

final_list_75 = []
final_list_50 = []
for small_list in large_list:
    length = len(small_list)
    three_quarter = (length//4)*3
    quarter = length - three_quarter
    first_half = length//2
    second_half = length - first_half
    
    three_quarter_list = []
    for i in range(three_quarter):
        three_quarter_list.append(1)
    for i in range(quarter):
        three_quarter_list.append(0)    
    shuffle(three_quarter_list)
        
    half_list = []
    for i in range(first_half):
        half_list.append(1)
    for i in range(second_half):
        half_list.append(0) 
    shuffle(half_list)
        
    final_list_item_75 = []
    for i in range(len(small_list)):
        if three_quarter_list[i] == 1:
            final_list_item_75.append(small_list[i])
    final_list_75.append(final_list_item_75)
    
    final_list_item_50 = []
    for i in range(len(small_list)):
        if half_list[i] == 1:
            final_list_item_50.append(small_list[i])
    final_list_50.append(final_list_item_50)    
    
#print (final_list_75)
final_list_75_str = []
for single_list in final_list_75:
    string = ''
    for item in single_list:
        string = string + ' ' + item
    final_list_75_str.append(string)

#print (final_list_50)
final_list_50_str = []
for single_list in final_list_50:
    string = ''
    for item in single_list:
        string = string + ' ' + item
    final_list_50_str.append(string)

for string in final_list_75_str:
    print (string)
print ('=====')
for string in final_list_50_str:
    print (string)