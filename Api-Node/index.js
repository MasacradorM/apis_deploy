const express = require('express');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(express.json());

let products = [
  { id: 1, name: 'Laptop', price: 1200.00, stock: 10, category: 'Electrónica' },
  { id: 2, name: 'Mouse', price: 25.99, stock: 50, category: 'Periféricos' }
];

// GET / - Mensaje de bienvenida
app.get('/', (req, res) => {
  res.json({ message: 'API Node.js - Gestión de Productos funcionando' });
});

// GET /products - Listar todos los productos
app.get('/products', (req, res) => {
  res.json(products);
});

// GET /products/:id - Obtener producto por ID
app.get('/products/:id', (req, res) => {
  const product = products.find(p => p.id === parseInt(req.params.id));
  if (!product) return res.status(404).json({ message: 'Producto no encontrado' });
  res.json(product);
});

// POST /products - Crear nuevo producto
app.post('/products', (req, res) => {
  const { name, price, stock, category } = req.body;
  if (!name || price === undefined || stock === undefined || !category) {
    return res.status(400).json({ message: 'Campos requeridos: name, price, stock, category' });
  }
  const newProduct = {
    id: products.length ? products[products.length - 1].id + 1 : 1,
    name,
    price: parseFloat(price),
    stock: parseInt(stock),
    category
  };
  products.push(newProduct);
  res.status(201).json(newProduct);
});

// PUT /products/:id - Actualizar producto
app.put('/products/:id', (req, res) => {
  const index = products.findIndex(p => p.id === parseInt(req.params.id));
  if (index === -1) return res.status(404).json({ message: 'Producto no encontrado' });

  products[index] = {
    ...products[index],
    name: req.body.name ?? products[index].name,
    price: req.body.price !== undefined ? parseFloat(req.body.price) : products[index].price,
    stock: req.body.stock !== undefined ? parseInt(req.body.stock) : products[index].stock,
    category: req.body.category ?? products[index].category
  };

  res.json(products[index]);
});

// DELETE /products/:id - Eliminar producto
app.delete('/products/:id', (req, res) => {
  const index = products.findIndex(p => p.id === parseInt(req.params.id));
  if (index === -1) return res.status(404).json({ message: 'Producto no encontrado' });

  const deleted = products.splice(index, 1);
  res.json({ message: 'Producto eliminado', product: deleted[0] });
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Servidor corriendo en puerto ${PORT}`);
});
