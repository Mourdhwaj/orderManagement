import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { ThemeProvider, createTheme } from '@mui/material';
import CssBaseline from '@mui/material/CssBaseline';
import Navbar from './components/Navbar';
import OrderList from './components/OrderList';
import CreateOrder from './components/CreateOrder';
import OrderDetails from './components/OrderDetails';

const theme = createTheme({
  palette: {
    primary: {
      main: '#1976d2',
    },
    secondary: {
      main: '#dc004e',
    },
  },
});

function App() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Router>
        <Navbar />
        <Routes>
          <Route path="/" element={<OrderList />} />
          <Route path="/create" element={<CreateOrder />} />
          <Route path="/orders/:id" element={<OrderDetails />} />
        </Routes>
      </Router>
    </ThemeProvider>
  );
}

export default App; 