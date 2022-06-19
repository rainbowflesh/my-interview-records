import { createApp, h } from 'vue'
import MapDialog from './MapDialog.vue'

export const createDialog = (options: { title: any; content: any; ok: any; cancel: any }) => {
    const { title, content, ok, cancel } = options
    const div = document.createElement('div')
    document.body.appendChild(div)
    const close = () => {
        // app.unmount(div)
        app.unmount()
        div.remove()
    }
    const app = createApp({
        render() {
            return h(MapDialog, {
                visible: true,
                'onUpdate:visible': (newVisible: boolean) => {
                    if (newVisible === false) {
                        close();
                    }
                },
                title,
                ok, cancel
            }, { default() { return content } })
        }
    })
    app.mount(div)
}
