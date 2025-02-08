import User from "../assets/Icons/User"
import { followUser, getProfileById } from "../services/profileService"
import { useAuthContext } from "../context/AuthContext"
import { useCallback, useEffect, useMemo, useRef, useState } from "react"
import { debounce } from "lodash";
import LINKS_ICONS from "./LinksIcons"


export default function UserInfo({isFeed, idParam }) {
  const {user, isAuthenticated, logout} = useAuthContext()

  const [profile, setProfile] = useState({})
  const [isFriend, setIsFriend] = useState(false)
  const lastStateRef = useRef(false)
  const textFriend = isFriend ? 'Siguiendo' : 'Seguir'
  const secondId = isAuthenticated ? user.id : idParam

  useEffect(()=>{
    const getProfile = async () => {
      try{
        const response = await getProfileById(idParam, secondId )
        setProfile(response)
        setIsFriend(response.isFriend)
        lastStateRef.current = response.isFriend
      }catch (err){
        throw new Error(err)
      }
    }
    getProfile()
  },[idParam,secondId])

  const debouncedFollow = useMemo(
    () =>
      debounce(async (newState, idParam, setIsFriend) => {
        try {
          await followUser(idParam, user.id, newState )
          lastStateRef.current = newState
        } catch (err) {
          setIsFriend(lastStateRef.current)
          alert(err.message)
        }
      }, 500),
    [user?.id]
  )
  
  const handleFollow = useCallback(() => {
    setIsFriend((prev) => {
      const newState = !prev
      debouncedFollow(newState, idParam, setIsFriend)
      return newState
    })
  }, [idParam, debouncedFollow])


  
  if(!profile) return <div className="w-full text-center">Usuario no encontrado</div>

  const {name, job, avatarUrl, links, friends } = profile
  
  return(
    <div className={`max-w-[1200px]  ${isFeed ? "md:p-0" : "px-3 py-4 lg:mx-auto"}`}>
      <header 
        className={isFeed 
          ? "bg-primary-100/30 flex flex-col gap-4 items-center p-5 rounded-2xl"
          : "md:bg-primary-100/30 flex flex-col gap-4 items-center px-3 md:flex-row md:gap-20 md:p-8 rounded-2xl max-w-[1200px] "
        }
      >
        <section className="flex flex-col items-center gap-2">
          {
            avatarUrl 
              ? <img src={avatarUrl} alt="Profile" className="w-36 max-w-none aspect-square rounded-full"/>
              : <User className="w-36 h-36 max-w-none aspect-square rounded-full"/>
          }
          <p className="paragraph text-center w-full whitespace-nowrap">{friends} AMIGOS</p>
        </section>
        <section className={`w-full ${isFeed? ' max-w-60': 'md:w-auto'}`} >
          <div className="grid ">
            <h2 className={`h1-sen ${isFeed ? 'text-center': '' }`}>{name}</h2>
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
            <section className={`md:flex-col gap-2 ${isFeed ? '': 'flex '}`}>
            {(isAuthenticated && idParam === user?.id.toString() ) && (
              <>
                <button 
                  onClick={logout}
                  className="button-user-info"
                >
                  Logout
                </button>
                <button className="button-user-info">
                  Editar
                </button>
              </>
            )}
            {
              isFriend && <button className={`button-user-info ${isFriend ? 'is-friend':''}`} onClick={handleFollow}>
                <span>{textFriend}</span>
              </button>
            }
            </section>
      </header>
    </div>
  )
}