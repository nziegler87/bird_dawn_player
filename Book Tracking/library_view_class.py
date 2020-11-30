import tkinter as tk


class LibraryView:

    def __init__(self):
        self.window = tk.Tk()
        self.header = tk.Label(text="Library Model", foreground="blue", background="GAINSBORO")
        self.header.pack()
        self.button = tk.Button(text="Load CSV File")
        self.button.pack()
        self.window.mainloop()
