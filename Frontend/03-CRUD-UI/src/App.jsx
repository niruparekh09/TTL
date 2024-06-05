import { useEffect, useState } from 'react';
import axios from 'axios';

function App() {
  const [data, setData] = useState([]);

  const fetchData = async () => {
    try {
      const response = await axios.get("http://localhost:8080/employee");
      setData(response.data);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const deletePost = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/employee/${id}`);
      console.log("Post deleted:", id);
      setData(data.filter((post) => post.id !== id));
    } catch (error) {
      console.error("Error deleting post:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div></div>
  );
}

export default App;
