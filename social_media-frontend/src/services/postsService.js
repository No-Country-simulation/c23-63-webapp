import axiosApi from './axiosApi';

import posts from '../models/post_details.json'
import postsFeed from '../models/post_feed.json'

export const createPost = async (postData) => {
  try {
    const { data } = await axiosApi.post('api/posts/create', postData)
    return data
  } catch (err){
    console.error("Error al crear el post:", err);
    throw err
  }
}

// export const getPostById = async (id) => {
//   try {
//     return await axiosApi.get(`/post/${id}`)
//   }catch(err){
//     const message = e?.response
//       ? ERROR_MESSAGES[e.response.status] || ERROR_MESSAGES.default
//       : ERROR_MESSAGES.connectionError
//     throw new Error(message)
//   }
// }

export const getPostById = (id) => {
  try {
    const post = posts.find(
      (p) => p.postId.toString() === id
    )
    return post
  }catch{
    throw new Error('no hay post')
  }
  // if (post){
  //   return post
  // }else{
  // }
}

export const getPostsFeed = () => {
  return postsFeed
}

// export const getPostsFeed = async () => {
//   try {
//     return await axiosApi.get('posts')
//   }catch(e){
//     const message = e?.response
//       ? ERROR_MESSAGES[e.response.status] || ERROR_MESSAGES.default
//       : ERROR_MESSAGES.connectionError
//     throw new Error(message)
//   }
// }