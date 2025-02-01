import profiles from '../models/profiles.json'
// import posts from '../models/posts_profile.json'
import axiosApi from './axiosApi'

export const getProfileById = (id) => {
  const profile = profiles.profiles.find(
    (currentProfile) => currentProfile.user_id === parseInt(id)
  )
  return profile || null
}

// export const getPostsByProfileId = (id) => {
//   if(posts.posts_profile.user_id === parseInt(id)) {
//     return { 
//       count_posts: posts.posts_profile.count_posts,
//       posts: posts.posts_profile.posts
//     }
//   }else {
//     return{}
//   }
// }

export const getPostsByProfileId = async (id) => {
  try {
    const response = await axiosApi.get(`api/posts/${id}`)
    console.log(response)
    console.log(response.data)
    return response
  }catch(err){
    console.log(err)
  }
}