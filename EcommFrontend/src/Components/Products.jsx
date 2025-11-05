import React from 'react'
import ProductPage from './ProductPage';
import { useEffect, useState } from 'react'
import Error from './Error';

function Products({Product}) {

    const [selectedProduct, setSelectedProduct] = useState(null);

    const handleProduct = (data) =>{
        setSelectedProduct(data);
    }

  return (
    <div className='products-container p-2'>
        {
            !selectedProduct ? 
            <> 
                {Product.length ? (
                    <div
                        className='product flex gap-5 justify-start items-start'>
                        {
                            Product.map((data)=>(
                                <div
                                    onClick={() => handleProduct(data)}
                                    className=' hover:border cursor-pointer h-fit w-xs p-2'
                                    key={data.id}>
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

                                    <div className='flex justify-between'>
                                        <p className='text-green-500'>{data.stockQuantity} Available</p>
                                        <p className='text-gray-500'>{data.releaseDate}</p>
                                    </div>
                                </div>
                            ))
                        }
                    </div>
                ) : (
                    <Error />
                )
            }
        </>
        : <ProductPage selectedProduct={selectedProduct} />
        } 
    </div>
  )
}

export default Products