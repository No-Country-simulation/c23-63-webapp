import Discord from "../assets/Icons/Discord"
import Dribble from "../assets/Icons/Dribble"
import GitHub from "../assets/Icons/GitHub"
import Instagram from "../assets/Icons/Instagram"
import LinkedIn from "../assets/Icons/LinkedIn"
import Youtube from "../assets/Icons/Youtube"
import User from "../assets/Icons/User"
import { getProfileById } from "../services/profileService"
import { useAuthContext } from "../context/AuthContext"

export default function UserInfo({isFeed, idParam }) {
  const {user, isAuthenticated, logout} = useAuthContext()

  const isOwnProfile = idParam.toString() === user?.id
  console.log("profile", isOwnProfile)
  
  // const {name, bio, avatar_url, links, friends} = getProfileById(user.id)
  const {name, job, avatar_url, links, friends} = getProfileById(idParam)
  const LINKS_ICONS = {
    discord: <Discord/>,
    dribble: <Dribble/>,
    linkedin: <LinkedIn/>,
    github: <GitHub/>,
    instagram: <Instagram/>,
    youtube: <Youtube/>
  }
  
  return(
    <div className={`max-w-[1200px] lg:mx-auto ${isFeed ? "md:p-0" : "px-3 py-4"}`}>
      <header 
        className={isFeed 
          ? "bg-primary-100/30 flex flex-col gap-4 items-center p-5 rounded-2xl"
          : "md:bg-primary-100/30 flex flex-col gap-4 items-center px-3 md:flex-row md:gap-20 md:p-8 rounded-2xl max-w-[1200px] "
        }
      >
        <section className="flex flex-col items-center gap-2">
          {
            avatar_url 
              ? <img src={avatar_url} alt="Profile" className="w-36 max-w-none aspect-square rounded-full"/>
              : <User />
          }
          <p className="paragraph text-center w-full whitespace-nowrap">{friends} Amigos</p>
        </section>
        <section >
          <div className="grid ">
            <h2 className={`name ${isFeed ? 'text-center': '' }`}>{name}</h2>
            <h3 className={`job ${isFeed ? 'text-center': '' }`}>{job}</h3>
          </div>
          <ul className={isFeed ? "mt-4 flex justify-around items-center":"mt-4 flex gap-3 items-center"}>
            {
              links?.map((link, index) => {
                const Icon = LINKS_ICONS[link.type]
                return (
                  <li key={index}>
                    <a href={link.url} target="_blank" >{Icon}</a>
                  </li>
                )
              })
            }
          </ul>
        </section >
        {
          isAuthenticated && (
            <section className={`hidden ${isFeed ? '': 'md:block'}`}>
              <button 
              onClick={logout}
              className="w-full p-4 rounded-xl bg-primary-800"
            >
              logout
            </button>
            </section>
          )
        }
      </header>
    </div>
  )
}