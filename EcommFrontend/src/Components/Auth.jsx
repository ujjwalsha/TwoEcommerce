import React, { useState } from 'react'
import Navbar from './Navbar'
import axios from 'axios';
import toast, {Toaster} from 'react-hot-toast'
import PersonIcon from '@mui/icons-material/Person';
import LockIcon from '@mui/icons-material/Lock';
import EmailIcon from '@mui/icons-material/Email';
import PhoneAndroidIcon from '@mui/icons-material/PhoneAndroid';

function Auth({handleLocation, Location}) {

    const [username, setUsername] = useState("")
    const [password, setpassword] = useState("")
    const [email, setemail] = useState("");
    const [phone, setPhone] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [isLoginMode, setIsLoginMode] = useState(true);

    const handleSubmit = async(e) =>{
        e.preventDefault();

        if(isLoginMode)
        {
            try{
                const res = await axios.post("http://localhost:8080/api/users/login",
                    {username, password},
                    {
                        headers: {
                            "Content-Type": "application/json"
                        },
                    }
                )
                
                toast.success('Logged in successfully!')
            }
            catch(error)
            {
                toast.error("Incorrect Credentials");
            }
    
            setUsername("");
            setpassword("");
        }
        else{
            try{
                console.log(username, email, password, phone);

                const res = await axios.post("http://localhost:8080/api/users/register",
                    {username, email, password, phone},
                    {
                        headers:{
                            "Content-Type": "application/json"
                        },
                })

                console.log("response is : ", res);

                toast(res, {
                    icon: '✌️',
                 });
                
            }
            catch(error)
            {
                const result = error.response.data;
                console.log(result);
                 toast(result.message, {
                    icon: '😒',
                 });
            }

            setPhone("");
            setUsername("");
            setemail("");
            setpassword("");
        }



    }


  return (
    <div className='Auth-container'>
        <Navbar handleLocation={handleLocation} Location={Location} />
        <div className='login-container flex mt-20 justify-center'>

            <form className='flex p-2 flex-col  items-center border gap-2 '>


                <div className='relative border w-xs  flex items-center'>
                    <input 
                        type="text" 
                        placeholder='Enter username'
                        value={username}
                        className='p-2 border w-xs  font-semibold'
                        required
                        onChange={(e) => setUsername(e.target.value)}
                    />
                    <PersonIcon className='right-4 absolute text-gray-300 '></PersonIcon>
                </div>
    
            
                {
                    !isLoginMode && (

                        <>
                            <div className='relative border w-xs  flex items-center' >
                                <input 
                                    type="text" 
                                    placeholder='Enter your Email'
                                    value={email}
                                    className='p-2 border w-xs font-semibold'
                                    required
                                    onChange={(e) => setemail(e.target.value)}
                                />
                                <EmailIcon className='right-4 absolute text-gray-300 '></EmailIcon>
                            </div>

                            <div className='relative border w-xs  flex items-center' >
                                <input 
                                    type="text" 
                                    placeholder='Enter Phone Number'
                                    value={phone}
                                    className='p-2 border w-xs font-semibold'
                                    required
                                    onChange={(e) => setPhone(e.target.value)}
                                />
                                <PhoneAndroidIcon className='right-4 absolute text-gray-300'></PhoneAndroidIcon>
                            </div>
                        </>
                    )
                }

                <div className='relative border w-xs  flex items-center'>
                    <input 
                        type="password" 
                        placeholder='Enter password' 
                        value={password}
                        className='p-2 border w-xs font-semibold'
                        required
                        onChange={(e) => setpassword(e.target.value)}
                    />
                    <LockIcon className='right-4 absolute text-gray-300'></LockIcon>
                </div>
                    
                

                {
                    !isLoginMode && (
                        <input 
                            type="password" 
                            placeholder='Confirm Password' 
                            value={confirmPassword}
                            className='p-2 border w-xs font-semibold'
                            required
                            onChange={(e) => setConfirmPassword(e.target.value)}
                        />
                    )
                }

                {
                    isLoginMode && (
                        <div className='flex  justify-end w-full'>
                                <a href="">Forget Password!</a>
                        </div>
                    )
                }
                    
                <button 
                    className='bg-white w-full text-black font-semibold cursor-pointer p-2 hover:border'
                    onClick={handleSubmit}
                    type='submit'
                >Submit</button>
                <Toaster
                    position="top-center"
                    reverseOrder={false}
                />



                <div className='flex justify-center'>
                    {
                        isLoginMode ? 
                            <p className='text-gray-500'>Don't have an account:
                            <a 
                                className='text-white cursor-pointer hover:underline'
                                onClick={()=> setIsLoginMode(false)}
                            >Sign Up</a></p>
        
                         :
                            <p className='text-gray-500'> you have an account:
                            <a 
                                className='text-white cursor-pointer hover:underline'
                                onClick={() => setIsLoginMode(true)}
                            >Sign in</a></p>
                    }
                </div>
            </form>
        </div>
    </div>
  )
}

export default Auth