import profiles from '../models/profiles.json'
// import posts from '../models/posts_profile.json'
import axiosApi from './axiosApi'
import { ERROR_MESSAGES } from './errorMessages'



export const getProfileById = (id) => {
  const profile = profiles.profiles.find(
    (currentProfile) => currentProfile.user_id === parseInt(id)
  )
  return profile || null
}

// export const getPostsByProfileId = (id) => {
//   if(posts.postsProfile.userId === parseInt(id)) {
//     return { 
//       countPosts: posts.postsProfile.countPosts,
//       posts: posts.postsProfile.posts
//     }
//   }else {
//     return{}
//   }
// }

export const getPostsByProfileId = async (id) => {
  try {
    const response = await axiosApi.get(`api/posts/${id}`)
    return response.data
  }catch(err){
    const message = err?.response
      ? ERROR_MESSAGES[err.response.status] || ERROR_MESSAGES.default
      : ERROR_MESSAGES.connectionError
    throw new Error(message)
  }
}