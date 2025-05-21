import React from 'react';
import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';

function Navbar() {
  return (
    <AppBar position="static">
      <Toolbar>
        <ShoppingCartIcon sx={{ mr: 2 }} />
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          Order Management
        </Typography>
        <Box>
          <Button
            color="inherit"
            component={RouterLink}
            to="/"
          >
            Orders
          </Button>
          <Button
            color="inherit"
            component={RouterLink}
            to="/create"
          >
            Create Order
          </Button>
        </Box>
      </Toolbar>
    </AppBar>
  );
}

export default Navbar; 