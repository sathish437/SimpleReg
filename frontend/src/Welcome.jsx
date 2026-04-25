import { useNavigate } from "react-router-dom"
import { logout, getToken, getWelcome } from "./services/authService"
import { useEffect, useState } from "react"

function Welcome(){
    const navigate = useNavigate()
    const userName = localStorage.getItem('userName') || 'User'
    const [message, setMessage] = useState('')
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState('')

    useEffect(() => {
        // Check if user is authenticated
        if(!getToken()){
            navigate('/login')
            return
        }

        // Call protected backend endpoint
        const fetchWelcome = async () => {
            try {
                const welcomeMessage = await getWelcome()
                setMessage(welcomeMessage)
            } catch (err) {
                setError(err.message)
                // If token invalid, redirect to login
                if(err.message.includes('Session expired')){
                    navigate('/login')
                }
            } finally {
                setLoading(false)
            }
        }

        fetchWelcome()
    }, [navigate])

    const handleLogout = () => {
        logout()
        navigate('/login')
    }

    return(
        <>
            <main className='flex min-h-screen w-full bg-blue-600 justify-center items-center p-4 sm:p-6 lg:p-8'>
                <section className='rounded-3xl p-6 sm:p-8 lg:p-10 bg-white w-full max-w-[500px] shadow-2xl text-center'>
                    <h1 className='text-2xl sm:text-3xl font-bold text-gray-800 mb-4'>
                        Welcome, <span className='text-blue-600'>{userName}</span>!
                    </h1>

                    {loading && (
                        <div className='text-gray-600 mb-6 text-base sm:text-lg'>
                            <p>Loading...</p>
                        </div>
                    )}

                    {error && (
                        <div className='bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-6'>
                            <p className='font-medium'>{error}</p>
                        </div>
                    )}

                    {!loading && !error && message && (
                        <div className='bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-6'>
                            <p className='font-medium'>✓ {message}</p>
                        </div>
                    )}

                    <button
                        onClick={handleLogout}
                        className='w-full sm:w-auto px-8 py-3 bg-red-500 hover:bg-red-600 active:scale-95 transition-all text-white font-medium rounded-2xl'
                    >
                        Logout
                    </button>
                </section>
            </main>
        </>
    )
}

export default Welcome
