import { Route, Routes } from 'react-router-dom'
import './App.css'

import Home from './pages/Home'
import Auth from './pages/auth/Auth'
import Login from './pages/auth/Login'
import Register from './pages/auth/Register'
import RecoverPassword from './pages/auth/RecoverPassword'

function App() {

  return (
    <>
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path='/auth'  element={<Auth />}>
          <Route path='login' element={<Login />} />
          <Route path='register' element={<Register />} />
          <Route path='recover-password' element={<RecoverPassword />} />
        </Route>
      </Routes>
    </>
  )
}

export default App
