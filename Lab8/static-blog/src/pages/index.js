import React from "react"
import {Link} from "gatsby-link"
import "./index.css"
import { graphql } from 'gatsby'
import {GatsbyImage, getImage, StaticImage} from 'gatsby-plugin-image'

const IndexPage = ({ data }) => {
  console.log(data)
  return (
    <div>
      {data.allMarkdownRemark.edges.map(({ node }) => (
        <div key={node.id} className="article-box">
          <Link
            to={node.frontmatter.slug}
            style={{ textDecoration: "none", color: "inherit" }}
          > 
          <p className="img">
            
          {node.frontmatter.thumb ? (
            <GatsbyImage
              image={getImage(node.frontmatter.thumb)}
              alt={node.frontmatter.title}
              width={500}
            />
          ) : (
            <StaticImage
              src="../images/example.png"
              alt="example"
              width={500}
            />
          )}

          </p>
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
