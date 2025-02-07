import { Link, useLocation } from "react-router-dom";
import { LogotipoNexo } from "../assets/Icons/Nexo";
import { useAuthContext } from "../context/AuthContext";
// import IconSearch from "../assets/Icons/IconSearch";
// import { useState } from "react";

export default function NavBar() {
  const { isAuthenticated, user } = useAuthContext()
  const location = useLocation()

  const isActive = (path) => location.pathname === path
  // const [menuOpen, setMenuOpen] = useState(true)


  return (
    <nav className="bg-primary-400 sticky top-0 z-10 mb-3 nav-bar flex p-4 justify-between items-center w-full">
        <Link to={'/'}>
          <LogotipoNexo />
        </Link>
      <ul className="relative">
        {/* <li>
          <button className="link-navbar active md:hidden" onClick={()=>setMenuOpen(!menuOpen)}>☰</button>
        </li> */}
        <ul className={'flex gap-4'}>
          {isAuthenticated ? (
            <>
              {/* <li>
                <div className="flex gap-4 w-80">
                  <input type="text" className="w-full px-3 py-2 rounded-2xl paragraph text-gray-400 border-2 bg-primary-400 border-transparent focus:bg-white focus:outline-none border-primary-100"/>
                  <button className="flex items-center p-1">{<IconSearch/>}</button>
                </div>
              </li> */}
              <li>
                <Link className={`link-navbar ${isActive('/') ? 'active': ''}`} to={'/'}>Inicio</Link>
              </li>
              <li>
                <Link className={`link-navbar ${isActive(`/profile/${user.id}`) ? 'active': ''}`} to={`/profile/${user.id}`}>Perfil</Link>
              </li>
            </>
          ) : (
            <>
              <li>
                <Link className={`link-navbar active`} to={"/auth/login"}>Iniciar sesión</Link>
              </li>
              <li>
                <Link className={`link-navbar border-[1px] border-white`} to={"/auth/register"}>Registrarse</Link>
              </li>
            </>
          )}
        </ul>
      </ul>
    </nav>
  )
}