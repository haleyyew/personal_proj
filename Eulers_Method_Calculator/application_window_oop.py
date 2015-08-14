#Creating application window using objects

from Tkinter import *
from euler_method_diff_eqn import *
from ScrolledText import ScrolledText

class Application(Frame):

    def __init__(self, master):
        Frame.__init__(self, master)
        self.grid()
        self.create_widgets()

    def create_widgets(self):
        self.label_0 = Label(self, text = "Enter differential equation:")
        self.label_0.grid(row = 0, column = 0, sticky =W)  
        
        self.label_1 = Label(self, text = "y' = ")
        self.label_1.grid(row = 1, column = 0, sticky =W)    
        
        self.input_1 = Entry(self)
        self.input_1.grid(row = 1, column = 1, sticky = W)  
        
        self.label_2 = Label(self, text = "y")
        self.label_2.grid(row = 1, column = 2, sticky =W) 
        
        self.label_2 = Label(self, text = "Enter initial condition:")
        self.label_2.grid(row = 2, column = 0, sticky =W)        
        
        self.label_3 = Label(self, text = "y(")
        self.label_3.grid(row = 3, column = 0, sticky =W)  
        
        self.input_2 = Entry(self)
        self.input_2.grid(row = 3, column = 1, sticky = W)    
        
        self.label_4 = Label(self, text = ") = ")
        self.label_4.grid(row = 3, column = 2, sticky =W)       
        
        self.input_3 = Entry(self)
        self.input_3.grid(row = 3, column = 3, sticky = W)    
        
        self.label_5 = Label(self, text = "Approximate: y(")
        self.label_5.grid(row = 4, column = 0, sticky =W)
        
        self.input_4 = Entry(self)
        self.input_4.grid(row = 4, column = 1, sticky = W)  
        
        self.label_6 = Label(self, text = ")")
        self.label_6.grid(row = 4, column = 2, sticky =W)   
        
        self.label_7 = Label(self, text = "Step size: ")
        self.label_7.grid(row = 5, column = 0, sticky =W)     
        
        self.input_5 = Entry(self)
        self.input_5.grid(row = 5, column = 1, sticky = W)        
        
        self.button = Button(self)
        self.button["text"] = "Get Solution"
        self.button["command"] = self.calculate      
        self.button.grid(row = 6, column = 0, sticky = W)
        
        self.textbox = ScrolledText(self, wrap = WORD, width = 30, height = 10)
        self.textbox.grid(row = 7, column = 0, columnspan = 2, sticky = W)  
        
        self.label_8 = Label(self, text = "Give me a sample calculation:")
        self.label_8.grid(row = 8, column = 0, sticky =W)    
        
        self.button = Button(self)
        self.button["text"] = "Sample"
        self.button["command"] = self.sample      
        self.button.grid(row = 8, column = 1, sticky = W)        
        
    def calculate(self):
        """Increase the click count and display the new total"""
        df = float(self.input_1.get())
        h_accum = float(self.input_2.get())
        init = float(self.input_3.get())
        end = float(self.input_4.get())
        h = float(self.input_5.get())
        
        results = get_result (df, init, h, end, h_accum)
        self.textbox.delete(0.0, END)
        self.textbox.insert(0.0, results)      
        
    def sample(self):
        self.input_1.delete(0, 'end')
        self.input_2.delete(0, 'end')
        self.input_3.delete(0, 'end')
        self.input_4.delete(0, 'end')
        self.input_5.delete(0, 'end')
        
        self.input_1.insert(0, -5)
        self.input_2.insert(0, 0)
        self.input_3.insert(0, 1)
        self.input_4.insert(0, 4)
        self.input_5.insert(0, 0.25)


root = Tk()
root.title("Euler's Method Calculator")
root.geometry("700x500")
app = Application(root)


if __name__ == "__main__":
    root.mainloop()