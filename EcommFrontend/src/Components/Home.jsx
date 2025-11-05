
import React, { useEffect, useState } from 'react'

import Navbar from './Navbar';
import axios from 'axios'
import Products from './Products';

function Home({handleLocation, Location}) {

    const [Category, setCategory] = useState([]);
    const [Product, setProduct] = useState([]);
    const [categoryId, setCategoryId] = useState(0);
    

    useEffect(()=>{
        console.log("AA gye h");
        console.log("before categoryid", categoryId);
        
        axios.get(`http://localhost:8080/api/category/${categoryId}`)
        .then(response =>{
            console.log(response.data);
            setProduct(response.data)
            console.log("hi");
        })
        .catch(error =>{
            console.log(error);
        })

        axios.get(`http://localhost:8080/api/categories`)
        .then(response =>{
            setCategory(response.data)
            console.log(response.data);  
        })
        .catch(error =>{
            console.log(error);
            
        })

    },[categoryId])


    return (
        <div className='Home-container'>
            <Navbar handleLocation={handleLocation} Location={Location} />
            <div className='category-section flex gap-2 p-2 border '>
            <button 
                className='hover:bg-white hover:rounded-xl hover:text-black cursor-pointer  p-1'
                onClick={() => setCategoryId(0)}
            >All</button>
                
                {
                    Category.length ? (
                            Category.map((cat)=>(
                                <div className='flex' key={cat.id}>
                                    <button
                                        className='hover:bg-white hover:rounded-xl hover:text-black cursor-pointer  p-1'
                                        onClick={() => setCategoryId(cat.id)}
                                    >{cat.name}</button>
                                </div>
                        ))
                     
                        
                    ) 
                    : (
                        <p>No category 😒</p>
                    )
                }
            </div>
            
            {/* filter sorting products and results */}

            <div className='filter-section p-2 flex items-center justify-between'>
                <p className='font-semibold'>{Product.length} results</p>
                <select className="ui dropdown text-black  border font-semibold p-1 bg-white">
                    sort by:
                    <option value="">Featured</option>
                    <option value="1">Price: Low to High</option>
                    <option value="0">Price: High to Low</option>
                    <option value="0">Avg. Customer Review</option>
                    <option value="0">Newest Arrivals</option>
                    <option value="0">Best Sellers</option>
                </select>
            </div>
            
            
            <Products Product={Product} />
            
        </div>
    )
}

export default Home