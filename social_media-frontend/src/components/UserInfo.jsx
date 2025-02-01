import Discord from "../assets/Icons/Discord"
import Dribble from "../assets/Icons/Dribble"
import GitHub from "../assets/Icons/GitHub"
import Instagram from "../assets/Icons/Instagram"
import LinkedIn from "../assets/Icons/LinkedIn"
import User from "../assets/Icons/User"
import { getProfileById } from "../services/profileService"

export default function UserInfo({id}) {
  const {name, bio, avatar_url, links, followers} = getProfileById(id)
  const LINKS_ICONS = {
    discord: <Discord/>,
    dribble: <Dribble/>,
    linkedin: <LinkedIn/>,
    github: <GitHub/>,
    instagram: <Instagram/>
  }
  return(
    <header className="lg:bg-primary-100/30 flex flex-col lg:flex-row gap-4 lg:gap-20 items-center px-3 lg:p-8 rounded-2xl ">
        <section className="flex flex-col items-center gap-2">
          {
            avatar_url 
              ? <img src={avatar_url} alt="Profile" className="w-32 aspect-square rounded-full"/>
              : <User />
          }
          <p className="paragraph w-full whitespace-nowrap">{followers} seguidores</p>
        </section>
        <section className="w-full">
          <div className="grid">
            <h2 className="name">{name}</h2>
            <h3 className="bio">{bio}</h3>
          </div>
          <ul className="mt-4 flex gap-2">
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
        </section>
      </header>
  )
}