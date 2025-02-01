import { Outlet } from "react-router-dom";

export default function PageAuth() {
  return (
    <>
      <div className="flex justify-center w-full h-screen">
        <div className="hidden bg-gray-100 lg:flex flex-shrink-0 ">
          <img src="/imagen-login.webp" className="object-contain h-full" />
        </div>
        <main 
          className="flex-grow w-full max-h-[700px] h-full min-h-[600px] max-w-96 flex flex-col justify-center content-center gap-10 m-auto py-4">
          <Outlet />
        </main>
      </div>
    </>
  )
}