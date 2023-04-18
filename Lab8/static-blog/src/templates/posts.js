import React from "react"
import { graphql } from 'gatsby'
import Layout from '../components/Layout'
import {GatsbyImage, getImage, StaticImage} from 'gatsby-plugin-image'

// const Setup = ({ data }) => {
//   const post = data.markdownRemark
//   console.log(post)
//   return (
//     <div>
//       <h1>{post.frontmatter.title}</h1>
//       <h4 style={{ color: "rgb(165, 164, 164)" }}>
//         {post.frontmatter.author}{" "}
//         <span s tyle={{ fontSize: "0.8em" }}>
//           {" "}
//           - {post.frontmatter.date}
//         </span>
//       </h4>
//       <div dangerouslySetInnerHTML={{ __html: post.html }} />
//     </div>
//   )
// }
// export default Setup;

export default function Post({data}){
  const {html} = data.markdownRemark
  const {title, author, date, thumb} = data.markdownRemark.frontmatter
  return (
    <Layout>
    <div>
    {thumb ? (
            <GatsbyImage
              image={getImage(thumb)}
              alt={title}
              width={500}
            />
          ) : (
            <StaticImage
              src="../images/example.png"
              alt="example"
              width={500}
            />
          )}
    </div>
      <div>
        <h2>{title}</h2>
        <h3>{author}</h3>
        <h3>{date}</h3>
      </div>
      <div dangerouslySetInnerHTML={{__html: html}}/>
    </Layout>
  )
}
export const query = graphql`
query q($slug: String) {
  markdownRemark(frontmatter: {slug: {eq: $slug}}) {
    html
    frontmatter {
      author
      date
      title
      thumb {
        childImageSharp {
          gatsbyImageData
        }
      }
    }
  }
}
`
