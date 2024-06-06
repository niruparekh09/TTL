```javascript
import React, { useState, useEffect } from "react";
import axios from "axios";

const GetData = () => {
  // State to store the fetched data
  const [data, setData] = useState([]);

  // Function to fetch data using Axios
  const fetchData = async () => {
    // ... (same as before)
  };

  // Function to delete a post using Axios
  const deletePost = async (id) => {
    try {
      await axios.delete(`https://jsonplaceholder.typicode.com/posts/${id}`);
      console.log("Post deleted:", id);
      setData(data.filter((post) => post.id !== id));
    } catch (error) {
      console.error("Error deleting post:", error);
    }
  };

  // Call fetchData on component mount
  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div>
      <h2>Posts:</h2>
      <ul>
        {data.map((post) => (
          <li key={post.id}>
            {post.title}
            <button onClick={() => deletePost(post.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default GetData;
```