import { useState } from "react";

export default function NewPost({ setShowCreateForm }){
  const [image, setImage] = useState(null);
  const [title, setTitle] = useState("");
  const [preview, setPreview] = useState(null);

  const handleFileChange = (event) => {
    const file = event.target.files[0]; // Capturar archivo seleccionado

    if (file) {
      setImage(file);

      // Verificar si es una imagen válida
      if (file.type.startsWith("image/")) {
        const imageUrl = URL.createObjectURL(file);
        setPreview(imageUrl);
      } else {
        alert("Por favor, selecciona un archivo de imagen válido.");
        setImage(null);
        setPreview("");
      }
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (!image) {
      alert("Por favor, selecciona una imagen");
      return;
    }

    const formData = new FormData();
    formData.append("image", image);
    formData.append("title", title);

    try {
      const response = await fetch("http://localhost:8080/api/posts", {
        method: "POST",
        body: formData, // Enviamos el FormData
      });

      const data = await response.json();
      console.log("Imagen subida:", data);

      setShowCreateForm(false); // Cerrar formulario tras subir la imagen
    } catch (error) {
      console.error("Error al subir la imagen:", error);
    }
  };

  return (
    <>
      <button onClick={()=>{setShowCreateForm(false)}} className="text-red-300 bg-red-950 p-3 rounded-full">
        Cerrar
      </button>
      <h2 className="text-xl font-bold">Crear nueva publicación</h2>
      <form onSubmit={handleSubmit} className="space-y-3">
        <input
          className="input"
          type="text"
          placeholder="Título"
          onChange={({target})=> setTitle(target.value)}
          value={title}
          required
        />
        <input type="file" accept="image/*" onChange={handleFileChange} />

        {preview && (
          <div className="mt-2">
            <p className="text-sm text-gray-600">Vista previa:</p>
            <img
              src={preview}
              alt="Vista previa"
              className="w-full max-w-xs rounded-lg shadow-md"
            />
          </div>
        )}

        <button type="submit" className="bg-blue-500 text-white px-4 py-2 rounded">
          Subir Publicación
        </button>
      </form>
    </>
  );
}