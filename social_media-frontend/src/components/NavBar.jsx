import { LogotipoNexo } from "../assets/Icons/Nexo";

export default function NavBar() {
  return (
    <nav className="bg-yellow-600 flex justify-between items-center p-4 sticky top-0 z-10 mb-3">
      <LogotipoNexo />
      <ul className="flex gap-4">
        <li >
          <a href="/auth/login">Iniciar sesión</a>
        </li>
        <li>
          <a href="/auth/register">Registrarse</a>
        </li>
      </ul>
    </nav>
  )
}