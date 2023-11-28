from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
import csv

app = FastAPI()

class Book(BaseModel):
    title: str
    author: str
    available: bool

# Caminho para o arquivo CSV
csv_file_path = 'biblioteca.csv'

def read_csv():
    try:
        with open(csv_file_path, 'r', newline='', encoding='utf-8') as file:
            reader = csv.DictReader(file)
            return list(reader)
    except FileNotFoundError:
        with open(csv_file_path, 'w', newline='', encoding='utf-8') as file:
            fieldnames = ['id', 'title', 'author', 'available']
            writer = csv.DictWriter(file, fieldnames=fieldnames)
            writer.writeheader()
        with open(csv_file_path, 'r', newline='', encoding='utf-8') as file:
            reader = csv.DictReader(file)
            return list(reader)


def write_csv(data):
    with open(csv_file_path, 'w', newline='', encoding='utf-8') as file:
        fieldnames = ['id', 'title', 'author', 'available']
        writer = csv.DictWriter(file, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(data)

@app.get("/books", response_model=list[Book])
async def get_books():
    books_data = read_csv()
    return books_data

@app.get("/books/{book_id}", response_model=Book)
async def get_book(book_id: int):
    books_data = read_csv()
    for book in books_data:
        if int(book['id']) == book_id:
            return book
    raise HTTPException(status_code=404, detail="Book not found")

@app.post("/books", response_model=Book)
async def create_book(book: Book):
    books_data = read_csv()
    
    # Encontrar o próximo ID disponível
    next_id = max(int(book['id']) for book in books_data) + 1 if books_data else 1
    
    new_book = {'id': next_id, 'title': book.title, 'author': book.author, 'available': book.available}
    books_data.append(new_book)
    
    write_csv(books_data)
    return new_book

@app.put("/books/{book_id}", response_model=Book)
async def update_book(book_id: int, book: Book):
    books_data = read_csv()
    
    for b in books_data:
        if int(b['id']) == book_id:
            b.update({'title': book.title, 'author': book.author, 'available': book.available})
            write_csv(books_data)
            return b
    
    raise HTTPException(status_code=404, detail="Book not found")

@app.delete("/books/{book_id}", response_model=dict)
async def delete_book(book_id: int):
    books_data = read_csv()
    
    for i, book in enumerate(books_data):
        if int(book['id']) == book_id:
            del books_data[i]
            write_csv(books_data)
            return {"message": "Book deleted successfully"}
    
    raise HTTPException(status_code=404, detail="Book not found")
