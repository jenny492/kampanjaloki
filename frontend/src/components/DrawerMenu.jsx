// Menun pohjana käytetty Responsive Drawer -esimerkkiä MUI-dokumentaatiosta
// https://mui.com/material-ui/react-drawer/#responsive-drawer

import { AppBar, Box, Drawer, IconButton, List, ListItem, ListItemButton, ListItemIcon, ListItemText, Toolbar } from '@mui/material';
import { useState } from 'react';

import CreateIcon from '@mui/icons-material/Create';
import DashboardIcon from '@mui/icons-material/Dashboard';
import GroupIcon from '@mui/icons-material/Group';
import HomeIcon from '@mui/icons-material/Home';
import MenuIcon from '@mui/icons-material/Menu';
import { Link, Outlet } from 'react-router';

const drawerWidth = 240;

function Menu() {

    const [mobileOpen, setMobileOpen] = useState(false);
    const [isClosing, setIsClosing] = useState(false);

    const handleDrawerClose = () => {
        setIsClosing(true);
        setMobileOpen(false);
    };

    const handleDrawerTransitionEnd = () => {
        setIsClosing(false);
    };

    const handleDrawerToggle = () => {
        if (!isClosing) {
            setMobileOpen(!mobileOpen);
        }
    };

    const drawer = (
        <div>
            <List>
                <ListItem>
                    <ListItemButton component={Link} to='/'>
                        <ListItemIcon><HomeIcon /></ListItemIcon>
                        <ListItemText primary='Etusivu' />
                    </ListItemButton>
                </ListItem>
            </List>

            <List>
                <ListItem>
                    <ListItemButton component={Link} to='/campaigns'>
                        <ListItemIcon><GroupIcon /></ListItemIcon>
                        <ListItemText primary='Kampanjat' />
                    </ListItemButton>
                </ListItem>
            </List>

            <List>
                <ListItem>
                    <ListItemButton component={Link} to='/characters'>
                        <ListItemIcon><DashboardIcon /></ListItemIcon>
                        <ListItemText primary='Hahmot' />
                    </ListItemButton>
                </ListItem>
            </List>

            <List>
                <ListItem>
                    <ListItemButton component={Link} to='/search'>
                        <ListItemIcon><CreateIcon /></ListItemIcon>
                        <ListItemText primary='Haku' />
                    </ListItemButton>
                </ListItem>
            </List>

            <List>
                <ListItem>
                    <ListItemButton component={Link} to='/dashboard'>
                        <ListItemIcon><CreateIcon /></ListItemIcon>
                        <ListItemText primary='Hallintapaneeli' />
                    </ListItemButton>
                </ListItem>
            </List>

        </div>
    )

    return (
        <Box>
            <AppBar
                position='fixed'
                sx={{
                    width: { sm: `calc(100% - ${drawerWidth}px)` },
                    ml: { sm: `${drawerWidth}px` },
                }}>
                <Toolbar>
                    <IconButton onClick={handleDrawerToggle} color='inherit' sx={{ mr: 2, display: { sm: 'none' } }}>
                        <MenuIcon />
                    </IconButton>
                </Toolbar>
            </AppBar>



            <Drawer
                anchor='left'
                variant='temporary'
                open={mobileOpen}
                onTransitionEnd={handleDrawerTransitionEnd}
                onClose={handleDrawerClose}
                sx={{
                    display: { xs: 'block', sm: 'none' },
                    '& .MuiDrawer-paper': { boxSizing: 'border-box', width: drawerWidth },
                }}
                slotProps={{
                    root: {
                        keepMounted: true,
                    },
                }}
            >
                <Box
                    
                    onClick={handleDrawerClose}
                    onKeyDown={handleDrawerClose}>
                    {drawer}
                </Box>
            </Drawer>
            <Drawer
                anchor='left'
                variant='permanent'
                open
                sx={{
                    display: { xs: 'none', sm: 'block' },
                    '& .MuiDrawer-paper': { boxSizing: 'border-box', width: drawerWidth },
                }}
            >
                {drawer}

            </Drawer>

            <Outlet />
        </Box>
    );
}

export default Menu;