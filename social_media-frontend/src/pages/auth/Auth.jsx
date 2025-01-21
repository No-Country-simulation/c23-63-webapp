import { Outlet } from "react-router-dom";

export default function Auth() {
  return (
    <>
      <div className="p-3 lg:p-0 flex w-full h-screen">
        <div className="hidden bg-gray-100 lg:flex flex-shrink-0 ">
          <img src="/imagen-login.webp" className="object-contain h-full" />
        </div>
        <main className="flex-grow w-full max-w-96 flex flex-col gap-8 m-auto">
          <Outlet  />
        </main>
      </div>
    </>
  )
}