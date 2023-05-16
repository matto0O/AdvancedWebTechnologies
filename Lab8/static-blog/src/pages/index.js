import React from "react"
import {Link} from "gatsby-link"
import "./index.css"
import { graphql } from 'gatsby'
import {GatsbyImage, getImage, StaticImage} from 'gatsby-plugin-image'

const IndexPage = ({ data }) => {

  console.log(data)

  console.log(data.allMarkdownRemark.edges[0])
  
  const [searchQuery, setSearchQuery] = React.useState('')
  const filteredPosts = data.allMarkdownRemark.edges.filter(post => {
    const title = post.node.frontmatter.title.toLowerCase()
    const author = post.node.frontmatter.author.toLowerCase()
    const query = searchQuery.toLowerCase()
    return title.includes(query) || author.includes(query)
  })
  return (
    <div>
      <div class="search">
        
        <h1> Search posts:
          
        <input
            type="text"
            className="form-control"
            placeholder="Search for title or author"
            value={searchQuery}
            onChange={e => setSearchQuery(e.target.value)}
          />
        </h1>
      </div>
      {filteredPosts.map(({ node }) => (
        <div key={node.id} className="article-box">
          <Link
            to={node.frontmatter.slug}
            style={{ textDecoration: "none", color: "inherit" }}
          > 
          <div className="img">
            
          {node.frontmatter.thumb ? (
            <GatsbyImage
              image={getImage(node.frontmatter.thumb)}
              alt={node.frontmatter.title}
              height={200}
            />
          ) : (
            <StaticImage
              src="../images/example.png"
              alt="example"
              height={200}
              
            />
          )}

          </div>
            <h3 className="title">{node.frontmatter.title}</h3>
          </Link>
          <p className="author">
            Author: <i>{node.frontmatter.author}</i>
          </p>
          <p className="date">
            {node.frontmatter.date} {node.timeToRead}min read
          </p>
          <p className="excerpt">{node.excerpt}</p>
        </div>
      ))}
    </div>
  )
}
export default IndexPage;

export const query = graphql`
query HomePageQuery {
  allMarkdownRemark(sort: [{ frontmatter: { date: ASC } }]) {
    totalCount
    edges {
      node {
        frontmatter {
          title
          date
          author
          slug
          thumb {
            childImageSharp {
                gatsbyImageData
            }
          }
        }
        excerpt
        timeToRead
      }
    }
  }
}
`
