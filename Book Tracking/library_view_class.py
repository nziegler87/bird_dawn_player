import tkinter as tk
from tkinter.filedialog import askopenfilename, asksaveasfilename
from tkinter import messagebox


class LibraryView:

    def __init__(self):
        self.filepath = ""
        self.window_title = "Book Logger"

        # create and configure window
        window = tk.Tk()
        window.title(self.window_title)

        # create row/column for output display
        window.rowconfigure(1, minsize=800, weight=1)
        window.columnconfigure(0, minsize=800, weight=1)

        # create area where library information will be displayed
        text_display = tk.Text(master=window)

        # create area for button commands
        buttons_frame = tk.Frame(master=window, borderwidth=1, relief="raised")

        # add buttons
        new_button = tk.Button(master=buttons_frame, text="Create New CSV File")
        open_button = tk.Button(master=buttons_frame, text="Open an Existing CSV File", command=self.open_file)
        save_button = tk.Button(master=buttons_frame, text="Save", command=self.save_file)
        save_as_button = tk.Button(master=buttons_frame, text="Save As", command=self.save_as_file)
        new_button.grid(row=0, column=0, sticky="ew", padx=10, pady=10)
        open_button.grid(row=0, column=1, sticky="ew", padx=10, pady=10)
        save_button.grid(row=0, column=2, sticky="ew", padx=10, pady=10)
        save_as_button.grid(row=0, column=3, sticky="ew", padx=10, pady=10)

        text_display.grid(row=1, column=0, sticky="nsew")
        buttons_frame.grid(row=0, column=0, sticky="nsew")

    def save_file(self):
        if self.filepath == "":
            messagebox.showerror(title=None, message="No file has been opened.")
        else:
            print("Filed saved")  # stopped here

    def open_file(self):
        self.filepath = askopenfilename(filetypes=[("CSV files", "*.csv")])

        if not self.filepath:
            return

    def save_as_file(self):
        self.filepath = asksaveasfilename(
            defaultextension="csv",
            filetypes=[("CSV Files", "*.csv")])

        if not self.filepath:
            return


