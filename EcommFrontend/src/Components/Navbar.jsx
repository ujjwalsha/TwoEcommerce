import React, { useEffect, useState } from 'react'
import SearchIcon from '@mui/icons-material/Search';
import LocationPinIcon from '@mui/icons-material/LocationPin';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import { useLocation, useNavigate } from 'react-router-dom';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import axios from 'axios';


function Navbar({ handleLocation, Location}) {

    const navigate = useNavigate();
    const location = useLocation();
    
    const [City, setCity] = useState("");
    const [Pin, setPin] = useState("");
    const [Category, SetCategory] = useState([]);

    const handleContact = () =>{

        if(location.pathname === "/")
        {
            navigate("/contact");
        }
        else
            navigate("/")
    }

    const handleAbout = () => {

        if(location.pathname === "/")
        {
            navigate("/About");
        }
        else
            navigate("/")
    }

    const handleAccount = () =>{

        if(location.pathname === "/")
        {
            navigate("/Auth");
        }
        else
            navigate("/")
    }

    useState(() =>{

        axios.get(`http://localhost:8080/api/categories`)
        .then(response =>{
            SetCategory(response.data)
            console.log(response.data)
        })
        .catch(error =>{
            console.log(error);
        })
    }, [])

    useEffect(()=>{
        console.log("location is ",Location.address);

        if(Location.address !== undefined)
        {   
            setCity(Location.address.county || Location.address.city);
            setPin(Location.address.postcode)
        }
        
    },[handleLocation])

  return (
    <div className='navbar sticky top-0  backdrop-blur-lg p-2 flex gap-2.5 justify-between items-center'>
         <a href="/" className='font-semibold text-2xl line-through'>OneEcommerce</a>
        <div className='location flex'>
            <button  
                className='cursor-pointer'
                onClick={handleLocation}
            ><LocationPinIcon fontSize='large'></LocationPinIcon></button>
            <p className='font-semibold text-sm'>Deliver to {City.length  === 0 ? "Update Location" : City} {Pin.length === 0 ? "": Pin} </p>
        </div>
        
        <div className='search-bar border flex justify-center items-center'>

            <select className='w-30 h-10 bg-white text-black text-sm border-none'>
                <option value="">All</option>
                {
                    Category.length ?
                    (
                        Category.map((cat) =>(
                            <option className='text-black border-none ' key={cat.id}>{cat.name}</option>
                        ))
                    )
                    :
                    (
                        <option value="">Category</option>
                    )
                }
            </select>
           
            <input type="text" 
                className='w-[30em] p-2 border border-gray-400'
                placeholder='search products..........'
            />
            <button className='hover:border p-2 cursor-pointer font-semibold bg-white text-black'><SearchIcon className='font-semibold'></SearchIcon></button>
        </div>

        <div className='menu-items flex justify-between p-2 w-xs items-center'>
            <select className="ui dropdown font-semibold bg-white text-gray-700">
                <option value="1">EN</option>
                <option value="1">Hindi</option>
                <option value="0">French</option>
            </select>
            <button 
                className='hover:border p-2 cursor-pointer font-semibold'
                onClick={handleAbout}
            >About</button>
            <button 
                className='hover:border p-2 cursor-pointer font-semibold'
                onClick={handleContact}
            >Contact</button>
            <div className='cart-section  relative'>
                
                
                <button
                    className='cursor-pointer font-semibold'
                >
                    <p className='text-red-600 font-bold w-full bg-white text-center  rounded-full left-2 -top-2  absolute'>0</p>
                    <ShoppingCartIcon fontSize='medium'></ShoppingCartIcon>
                </button>
            </div>
            
            <button 
                className='hover:border p-1 cursor-pointer'
                onClick={handleAccount}
            ><AccountCircleIcon fontSize='large'></AccountCircleIcon></button>
        </div>
    </div>
  )
}

export default Navbar