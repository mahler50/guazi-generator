import FileUploader from '@/components/FileUploader';
import { makeGeneratorUsingPost } from '@/services/backend/generatorController';
import { ProFormInstance, ProFormItem } from '@ant-design/pro-components';
import { ProForm } from '@ant-design/pro-form';
import { Collapse, message } from 'antd';
import { saveAs } from 'file-saver';
import { useRef } from 'react';

interface Props {
  meta: API.GeneratorAddRequest | API.GeneratorEditRequest;
}

/**
 * 生成器制作
 * @param props
 */
export default (props: Props) => {
  const { meta } = props;
  const formRef = useRef<ProFormInstance>();

  const doSubmit = async (values: API.GeneratorMakeRequest) => {
    // 校验
    if (!meta.name) {
      message.error('请填写名称');
      return;
    }

    const zipFilePath = values.zipFilePath;
    if (!zipFilePath || zipFilePath.length < 1) {
      message.error('请双穿模板文件压缩包');
      return;
    }

    // 文件列表转 url
    // @ts-ignore
    values.zipFilePath = zipFilePath[0].response;

    try {
      const blob = await makeGeneratorUsingPost(
        {
          meta,
          zipFilePath: values.zipFilePath,
        },
        {
          responseType: 'blob',
        },
      );
      //  使用 file-saver 保存文件
      saveAs(blob, meta.name + '.zip');
    } catch (e: any) {
      message.error('下载失败' + e.message);
    }
  };
  /**
   * 表单视图
   */
  const formView = (
    <ProForm
      formRef={formRef}
      submitter={{
        searchConfig: {
          submitText: '制作',
        },
        resetButtonProps: {
          hidden: true,
        },
      }}
      onFinish={doSubmit}
    >
      <ProFormItem name="zipFilePath" label="模板文件">
        <FileUploader
          biz="generator_make_template"
          description="请上传压缩文包，打包时不要添加最外层目录! "
        />
      </ProFormItem>
    </ProForm>
  );

  return (
    <Collapse
      style={{
        marginBottom: 24,
      }}
      items={[
        {
          key: 'maker',
          label: '生成器制作工具',
          children: formView,
        },
      ]}
    />
  );
};
