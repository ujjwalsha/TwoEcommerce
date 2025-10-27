import React from 'react'
import SearchIcon from '@mui/icons-material/Search';
import LocationPinIcon from '@mui/icons-material/LocationPin';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

function Navbar() {





  return (
    <div className='navbar p-3 border-2 flex gap-2 justify-between items-center ml-10 mr-10'>
        <h1 className='font-semibold text-2xl line-through' >OneEcommerce</h1>
        <button className='hover:border p-1 cursor-pointer'><LocationPinIcon fontSize='large'></LocationPinIcon></button>
        <div className='search-bar border flex justify-center items-center'>
            
            <button className='hover:border p-2 cursor-pointer '>All</button>
            <input type="text" 
                className='w-xl p-2 border border-gray-600'
                placeholder='search products.....'
            />
            <button className='hover:border p-2 cursor-pointer font-semibold'><SearchIcon className='font-semibold'></SearchIcon></button>
            
        </div>

        <div className='menu-items flex justify-between p-2 w-xs items-center'>
            <button className='hover:border p-2 cursor-pointer'> EN </button>
            <a href="" className='hover:border p-2 cursor-pointer'>About</a>
            <a href="" className='hover:border p-2 cursor-pointer'>Contact</a>
            <button className='hover:border p-1 cursor-pointer'><AccountCircleIcon fontSize='large'></AccountCircleIcon></button>
        </div>
    </div>
  )
}

export default Navbar