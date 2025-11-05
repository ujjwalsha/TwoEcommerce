import React, { useState } from 'react'

function ProductPage({selectedProduct}) {

  console.log(selectedProduct);

  return (
    <div className='product-page flex flex-col gap-2'>
        <a 
          href="/" 
          className='hover:underline'
        >Home - </a>
        <div className='product-container flex justify-between'>
            <div className='image-container w-full'>
                  <img src={selectedProduct.imageUrl} alt={selectedProduct.name} />
            </div>

            <div className='product-detail flex flex-col w-full gap-1.4'>
                <h2 className='font-semibold text-2xl '>{selectedProduct.name}</h2>
                <a className='text-gray-300'>Visit the Store</a>
                <p className='font-semibold text-gray-400'>1200 ratings</p>
                <p className='text-black bg-gray-300 rounded-xl text-center w-1/4'>One Choice</p>
                <hr className='mt-2' />
                <p className='font-semibold text-4xl mt-1'><span className='text-red-600 font-light'>-40%</span>  $ {selectedProduct.price}</p>
                <div className='color-container mt-10 flex gap-2'>
                  <div className='bg-red w-10 h-10 bg-red-700 '>
                  </div>

                  <div className='bg-red w-10 h-10 bg-green-700 '>
                  </div>

                  <div className='bg-red w-10 h-10 bg-blue-700 '>
                  </div>

                  <div className='bg-red w-10 h-10 bg-yellow-700 '>
                  </div>

                </div>
                <p className='mt-3'> Brand : {selectedProduct.brand}</p>
                <p className='mt-3'> ReleaseDate : {selectedProduct.releaseDate}</p>
                <p className='mt-3'> Quantity : {selectedProduct.stockQuantity}</p>



                <div className='button-section flex w-full justify-evenly items-center mt-10'>
                    <button 
                      className='text-black bg-white rounded-xl w-1/3 p-2 cursor-pointer hover:bg-gray-200'
                    >Buy Now</button>
                    <button
                      className='text-black bg-white rounded-xl p-2 w-1/3 cursor-pointer hover:bg-gray-200'
                      >Add to Cart</button>
                </div>

                <hr className='mt-10' />
                <div className='about-product'>
                    <h2 className='font-semibold text-xl'>About the item</h2>
                    <p>{selectedProduct.description}</p>
                </div>
            </div>

        </div>


        <hr className='mt-10' />
        <h2 className='font-semibold text-2xl'>From manufacturer</h2>
        

    </div>
  )
}

export default ProductPage