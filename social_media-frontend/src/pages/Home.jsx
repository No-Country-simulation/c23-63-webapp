import axios from "axios";
import { useEffect, useState } from "react";

export default function Home() {
  const [user, setUser] = useState(null);

  useEffect(() => {
    axios
      .get("http://localhost:8080/user/info", { withCredentials: true })
      .then((response) => {
        setUser(response.data);
      })
      .catch(console.error);
  }, []);

  return (
    <div>
      <h1 className="text-4xl">Home</h1>
      {user ? (
        <div>
          <p>
            <strong>Nombre:</strong> {user.name}
          </p>
          <p>
            <strong>Email:</strong> {user.email}
          </p>
        </div>
      ) : (
        <p>Loading ..</p>
      )}
    </div>
  );
}
