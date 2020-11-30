import tkinter as tk

window = tk.Tk()

#address 1
#address 2
#city
#state
#zip

window.columnconfigure(0, weight=1, minsize=100)
window.rowconfigure([0,1,2], weight=1, minsize=20)

#header
header_frame = tk.Frame(master=window)
header_frame.grid(row=0, column=0)
label = tk.Label(master=header_frame, text="Address Entry Form")
label.pack()

#address entry
form_frame = tk.Frame(master=window)
form_frame.grid(row=1, column=0)

labels = ["Address 1:", "Address 2:", "City:", "State:", "Zip:"]

for num, label in enumerate(labels):
    label = tk.Label(master=form_frame, text=label)
    entry = tk.Entry(master=form_frame, width=50)
    label.grid(row=num, column=0, sticky="w")
    entry.grid(row=num, column=1)

#buttons
buttons_frame = tk.Frame(master=window)
buttons_frame.grid(row=2, column=0)

labels = ["Submit", "Clear"]
for label in labels:
    button = tk.Button(master=buttons_frame, text=label)
    button.pack(side=tk.LEFT, ipadx=10)
        
        

window.mainloop()
