import React, { useEffect, useState } from 'react'
import SearchIcon from '@mui/icons-material/Search';
import LocationPinIcon from '@mui/icons-material/LocationPin';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import { useLocation, useNavigate } from 'react-router-dom';


function Navbar({ handleLocation, Location}) {

    const navigate = useNavigate();
    const location = useLocation();
    
    const [City, setCity] = useState("");
    const [Pin, setPin] = useState("");

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


    useEffect(()=>{
        console.log("location is ",Location.address);

        if(Location.address !== undefined)
        {   
            setCity(Location.address.county || Location.address.city);
            setPin(Location.address.postcode)
        }
        
    },[handleLocation])



  return (
    <div className='navbar p-3 border-2 flex gap-2.5 justify-between items-center'>
         <a href="/" className='font-semibold text-2xl line-through'>OneEcommerce</a>
        <div className='location flex'>
            <button  
                className='p-1 cursor-pointer'
                onClick={handleLocation}
            ><LocationPinIcon fontSize='large'></LocationPinIcon></button>
            <p className='font-semibold'>Deliver to {City.length  === 0 ? "Update Location" : City} {Pin.length === 0 ? "": Pin} </p>
        </div>
        
        <div className='search-bar border flex justify-center items-center'>
            <button className='hover:border p-2 cursor-pointer '>All</button>
            <input type="text" 
                className='w-xl p-2 border border-gray-600'
                placeholder='search products.....'
            />
            <button className='hover:border p-2 cursor-pointer font-semibold bg-white text-black'><SearchIcon className='font-semibold'></SearchIcon></button>
        </div>

        <div className='menu-items flex justify-between p-2 w-xs items-center'>
            <select className="ui dropdown border font-semibold p-2 bg-[#242424]">
                <option value="">EN</option>
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
            <button 
                className='hover:border p-1 cursor-pointer'
                onClick={handleAccount}
            ><AccountCircleIcon fontSize='large'></AccountCircleIcon></button>
        </div>
    </div>
  )
}

export default Navbar