import { Avatar, Card } from 'antd';

interface Props {
  data: API.GeneratorVO;
}

/**
 * 作者信息
 * @param props
 * @constructor
 */
const AuthorInfo: React.FC<Props> = (props) => {
  const { data } = props;
  const user = data?.user;

  if (!user) {
    return <></>;
  }

  return (
    <div style={{ marginBottom: 16 }}>
      <Card.Meta
        title={user.userName}
        description={user.userProfile}
        avatar={<Avatar src={user.userAvatar} size={64} />}
      />
    </div>
  );
};

export default AuthorInfo;
