import NavBar from "../../components/NavBar";
import UserInfo from "../../components/UserInfo";
import { useAuthContext } from "../../context/AuthContext";
import PostsFeed from "./PostsFeed";
import ProfilesList from "./ProfilesList";

export default function PageHome() {
  const  { logout, user } = useAuthContext()
  return (
    <div className="pb-3">
      <NavBar/>
      <section className="max-w-screen-xl mx-auto md:grid grid-cols-7 lg:gap-4 justify-center">
        <aside className="hidden md:flex flex-col col-span-2 px-2 sticky top-[96px] justify-between h-[calc(100vh-109px)] ">
          <UserInfo isFeed={true} idParam={user.id}/>
          <section>
            <button 
              onClick={logout}
              className="w-full p-4 rounded-xl bg-primary-800"
            >
              logout
            </button>
          </section>
        </aside>
        <PostsFeed/>
        <aside className="hidden lg:flex flex-col col-span-2 max-w-xs gap-4 px-2 sticky top-[96px] self-start">
          <ProfilesList/>
        </aside>
      </section>
    </div>
  )
}