
import React, { useEffect, useState } from 'react'

import Navbar from './Navbar';
import axios from 'axios'
import { dividerClasses } from '@mui/material/Divider';
import Error from './Error';

function Home({handleLocation, Location}) {

    const [Category, setCategory] = useState([]);
    const [Product, setProduct] = useState([]);
    const [categoryId, setCategoryId] = useState(0);
    const [allTrigger, setAllTrigger] = useState(false);

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
                        <p>No category</p>
                    )
                }
            </div>

            <div className='products-container p-2'>
                {Product.length ? (
                        <div className='product flex gap-5 justify-start items-start'>
                            {
                                Product.map((data)=>(
                                    <div className=' hover:border cursor-pointer h-fit w-xs p-2' key={data.id}>
                                        <img 
                                            src={data.imageUrl || "https://placehold.co/600x400"}
                                            alt={data.imageUrl}
                                            className='w-xs h-xl'
                                        />
                                        <div className='flex justify-between p-1'>
                                            <h5 className='font-semibold'>{data.name}</h5>
                                            <p className='font-semibold'>${data.price}</p>
                                        </div>

                                        <div className='description p-1'>
                                            <p>{data.brand}</p>
                                            <p>{data.description}</p>
                                        </div>

                                        <div className='flex justify-center'>
                                            <p className='text-green-500'>{data.stockQuantity} Stocks Available</p>
                                        </div>
                                        
                                    </div>
                                ))
                            }
                        </div>
                    ) : (
                        <Error />
                    )
                }
            </div>
        </div>
    )
}

export default Home